def binary_search(list,item):
	lo = 0
	hi = len(list) - 1
	while lo <= hi :
		mid = (lo + hi)/2
		guess = list[mid]

		if guess == item :
			return mid

		if guess > item :
			hi = mid - 1
		else : 
			lo = mid + 1
	return None

my_list = [1,2,3,4,5,6,7,78,99]

print binary_search(my_list,3)
print binary_search(my_list,99)


