#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

typedef long long weight; // less typing

typedef struct edge {
  int u, v;
  weight w;
  bool operator<(const edge &cmp) const // so the priority queue can sort
  {
    return w > cmp.w;
  }
} Edge;

void deekstra(int target, const vector< vector<Edge> > &edges, vector<weight> &distance)
{
  priority_queue<Edge> queue;
  distance.assign(edges.size(), -1);
  queue.push((Edge){-1, target, 0});
  while(!queue.empty())
  {
    Edge n = queue.top();
    queue.pop(); // c++?
    if(distance[n.v] != -1)
      continue;
    distance[n.v] = n.w;
    for(auto neighbor : edges[n.v])
    {
      neighbor.w += n.w;
      queue.push(neighbor);
    }
  }
}

int depth_first_search(int i, vector<int> &matched, const vector< vector<int> > &bip, vector<int> &seen)
{
  if(seen[i]) return 0;
  seen[i] = 1;

  for(int j = 0; j < bip[i].size(); j++)
  {
    int jj = bip[i][j];
    if(matched[jj+bip.size()] == -1 || depth_first_search(matched[jj+bip.size()], matched, bip, seen))
    {
      matched[jj+bip.size()] = i;
      matched[i] = jj+bip.size();
      return 1;
    }
  }
  return 0;
}

int seen(const vector< vector<int> > &bip, vector<int> &matched)
{
    vector<int> seen(bip.size(), 0);
    for(int i = 0; i < bip.size(); i++)
      if(matched[i] == -1 && depth_first_search(i, matched, bip, seen) == 1)
        return 1;
    return 0;
}

int main(int argc, char *argv[])
{
  int n, m, c, i;
  scanf("%d%d%d", &n, &m, &c);

  int key_vertices[c];
  for(i = 0; i < c; i++)
    scanf("%d", &key_vertices[i]);

  vector< vector<Edge> > edges(n);
  while(m--)
  {
    Edge edge;
    scanf("%d%d%llu", &edge.u, &edge.v, &edge.w);
    edges[edge.u].push_back(edge); // store mutliple edges on a single vertex
  }

  vector<weight> cost;
  vector< vector<int> > bip(c);

  deekstra(0, edges, cost);
  for(i = 0; i < c; i++)
  {
    vector<weight> next_cost;
    deekstra(key_vertices[i], edges, next_cost);
    for(int j = 0; j < c; j++)
    {
      if(i != j && next_cost[key_vertices[j]] != -1 && cost[key_vertices[i]] + next_cost[key_vertices[j]] == cost[key_vertices[j]])
        bip[i].push_back(j);
    }
  }

  vector<int> matched(bip.size() * 2, -1);
  int matches = 0;
  for(;;matches++)
  {
    if(seen(bip, matched) ^ 1)
      break;
  }

  printf("%d\n", c-matches);
}
