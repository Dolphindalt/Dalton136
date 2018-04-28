import heapq
import math
import collections
import sys

sys.setrecursionlimit(5000)

class Junction:
    def __init__(self, index):
        self.index = index;
        self.visited = False
        self.cost = math.inf
        self.client_count = 0
        self.is_client = False
        self.prev = []
        self.edges = []
    def __lt__(self, other):
        return self.cost < other.cost
    def __repr__(self):
        return '{}'.format(self.index)

n, m, c = map(int, input().split())
junctions = [ Junction(i) for i in range(n)]
clients = [ junctions[int(i)] for i in input().split()]
for c in clients:
    c.is_client = True

for i in range(m):
    u, v, w = map(int, input().split())
    junctions[u].edges.append((junctions[v], w))

junctions[0].cost = 0
junctions[0].prev = None

queue = list(junctions)

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

# deekstra
while queue:
    j = heapq.heappop(queue)
    j.visited = True
    for n, w in j.edges:
        if n.visited:
            continue
        next_cost = j.cost + w
        next_client_count = j.client_count + n.is_client
        if next_cost < n.cost:
            n.cost = next_cost
            n.client_count = next_client_count
            n.prev = [j]
        elif next_cost == n.cost:
            if next_client_count > n.client_count:
                n.client_count = next_client_count
                n.prev = [j]
            elif next_client_count == n.client_count:
                n.prev.append(j)
    heapq.heapify(queue)

def client_count(path):
    return sum(c in path for c in clients)

trim_table = []

table = []
for c in clients:
    table.append([[client_count(path), c.cost, path] for path in find_path(c)])
    for path in table[-1]:
        trim_table.append(path)
table.sort(key=lambda r: max(t[1] for t in r))
trim_table.sort(key=lambda p: p[1])

#print(*trim_table,sep='\n')

def compare(path):
    pl = len(path)
    for row in trim_table:
        cmp_path = row[2]
        if pl > len(cmp_path):
            continue
        if set(path) == set(cmp_path):
            continue
        a = cmp_path[0:pl]
        if set(a) == set(path):
            return True
    return False

for i in range(len(trim_table)-1,-1,-1):
    path = trim_table[i][2]
    if compare(path):
        trim_table.pop(i)

for row in trim_table:
    path = row[2]
    row[2] = [c for c in path if c in clients]

for path in trim_table:
    for other in trim_table:
        if path is other:
            continue
        if path[1] == other[1] and set(path[2]) == set(other[2]):
            trim_table.remove(path)
    
for i in range(len(trim_table)-1, -1, -1):
    dik = {}
    tup = trim_table[i]
    path = tup[2]
    for c in path:
        dik[c] = False
    for compare in trim_table:
        if compare is tup:
            continue
        compare_path = compare[2]
        sl = min(len(compare_path), len(path))
        for j in range(sl):
            if path[j] == compare_path[j]:
                dik[path[j]] = True
    remove = True
    for c in path:
        if dik[c] is False:
            remove = False
            break
    if remove:
        trim_table.remove(tup)

print(len(trim_table))