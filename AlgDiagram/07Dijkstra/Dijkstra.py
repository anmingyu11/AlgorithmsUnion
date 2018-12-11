
#build graph map
graph = {}
graph["start"] = {}
graph["start"]["a"] = 6
graph["start"]["b"] = 2
graph["a"] = {}
graph["a"]["fin"] = 1
graph["b"] = {}
graph["b"]["a"] = 3
graph["b"]["fin"] = 5
graph["fin"] = {}
#build cost map
infinity = float("inf")
costs ={}
costs["a"] = 6
costs["b"] = 2
costs["fin"] = infinity
#build parent map
parents = {}
parents["a"] = "start"
parents["b"] = "start"
parents["fin"] = None
#build processed array to record node has been handle
processed = []

# the core code

def find_lowest_cost_node(costs):
	lowest_cost = float("inf")
	lowest_cost_node = None
	for node in costs:
		cost = costs[node]
		if cost < lowest_cost and node not in processed:
			lowest_cost = cost
			lowest_cost_node = node

	return lowest_cost_node

node = find_lowest_cost_node(costs)

while node is not None:
	cost = costs[node]#current node cost
	neighbors = graph[node]# get neighbors

	for n in neighbors.keys():
		new_cost = cost + neighbors[n]
		if new_cost < costs[n]:
			costs[n] = new_cost
			parents[n] = node
	processed.append(node)
	node = find_lowest_cost_node(costs)

print(costs)
print(parents)
