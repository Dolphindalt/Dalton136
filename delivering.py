import heapq
import math

class Junction:
    def __init__(self, index):
        self.index = index;
        self.visited = False
        self.cost = math.inf
        self.prev = []
        self.edges = []
    # def __eq__(self, other):
        # return self.cost == other.cost
    def __lt__(self, other):
        return self.cost < other.cost
    def __repr__(self):
        return f'{self.index}'

n, m, c = map(int, input().split())
junctions = [ Junction(i) for i in range(n)]
clients = [ junctions[int(i)] for i in input().split()]

for i in range(m):
    u, v, w = map(int, input().split())
    junctions[u].edges.append((junctions[v], w))

junctions[0].cost = 0
junctions[0].prev = None

queue = list(junctions)

heapq.heapify(queue)

# deekstra
while queue:
    j = heapq.heappop(queue)
    j.visited = True
    for n, w in j.edges:
        if n.visited:
            continue
        next_cost = j.cost + w
        if next_cost < n.cost:
            n.cost = next_cost
            n.prev = [j]
        elif next_cost == n.cost:
            n.prev.append(j)
    heapq.heapify(queue)

# pathfinder
def find_path(j):
    if j.prev:
        paths = []
        for p in j.prev:
            paths += find_path(p)
        for path in paths:
            path.append(j)
        return paths
    return [[j]]

for c in clients:
    print(*find_path(c), end='\n')

def client_count(path):
    return sum(c in path for c in clients)

table = []
for c in clients:
    table.append([(client_count(path), c.cost, path) for path in find_path(c)])
table.sort(key=lambda r: max(t[1] for t in r))

print(*table, sep='\n')

def prune_table():
    for row in table:
        pass
