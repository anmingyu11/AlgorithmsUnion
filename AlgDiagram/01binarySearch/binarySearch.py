def binary_search(list,item):
	low = 0
	high = len(list) - 1

	while (low <= high) :
		mid = low + high
		guess = list[mid]
		if guess == item :
			return mid
		if guess > item :
			high = mid -1
		else :
			low = mid + 1
	return None

my_list = [1,3,6,7,8,9]

print binary_search(my_list,6)
print binary_search(my_list,-1)
