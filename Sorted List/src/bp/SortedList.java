package bp;

public class SortedList implements ISortedList {
	private int MAX_SIZE = 5;
	private int sizeOfList = 0;
	private int[] list = new int[MAX_SIZE];
	private boolean duplicatesAllowed = true;

	@Override
	public int getSizeOfList() {
		return sizeOfList + 1;
	}

	@Override
	public boolean areDuplicatesAllowed() {
		return duplicatesAllowed;
	}

	@Override
	public void setDuplicatesAllowed(boolean pDuplicatesAllowed) {
		duplicatesAllowed = pDuplicatesAllowed;
	}

	@Override
	public void clear() {
		sizeOfList = 0;
	}

	@Override
	public void insert(int pValueToInsert) {
		if (sizeOfList >= MAX_SIZE) {
			System.out.println("Array is full.");
			return;
		}
		if (!duplicatesAllowed && find(pValueToInsert) != -1) return;
		list[sizeOfList++] = pValueToInsert;
	}

	@Override
	public void delete(int pValueToDelete) {
		int index = find(pValueToDelete);
		if (index == -1) return;
		for (int i = index; i < sizeOfList; i++) {
			list[i] = list[i + 1];
		}
		sizeOfList--;
	}

	@Override
	public void deleteAll(int pValueToDelete) {
		int deleted = 0;
		int[] indices = findAll(pValueToDelete);
		for (int i = 0; i < indices.length; i++) {
			for (int j = indices[i] - (deleted); j < sizeOfList; j++) {
				list[j] = list[j + 1];
			}
			sizeOfList--;
			deleted++;
		}
	}

	@Override
	public void initializeWithRandomData(int pSizeOfList) {
		for (int i = 0; i < pSizeOfList; i++) {
			insert(getRandomNumber(pSizeOfList*100));
		}
	}

	private int getRandomNumber(int pMaxValue) {
		int num = (int) (Math.random() * pMaxValue + 1);
		if (find(num) != -1 && !duplicatesAllowed)
			num = getRandomNumber(pMaxValue);
		return num;
	}

	@Override
	public int find(int pValueToFind) {
		for (int i = 0; i < sizeOfList; i++) {
			if (list[i] == pValueToFind) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int[] findAll(int pValueToFind) {
		SortedList found = new SortedList();
		for (int i = 0; i < sizeOfList; i++) {
			if (list[i] == pValueToFind) {
				found.insert(i);
			}
		}
		int[] foundArray = new int[found.getSizeOfList() - 1];
		for (int i = 0; i < foundArray.length; i++) {
			foundArray[i] = found.get(i);
		}
		return foundArray;
	}

	public int get(int pIndex) {
		return list[pIndex];
	}

	public void bubbleSort() {
		boolean swapped = true;
		int increments = 0, holder;
		while (swapped) {
			swapped = false;
			increments++;
			for (int i = 0; i < sizeOfList - increments; i++) {
				if (list[i] > list[i + 1]) {
					holder = list[i];
					list[i] = list[i + 1];
					list[i + 1] = holder;
					swapped = true;
				}
			}
		}
	}
	
	public void selectionSort() {
		int minIndex, tmp;
	      for (int i = 0; i < sizeOfList - 1; i++) {
	            minIndex = i;
	            for (int j = i + 1; j < sizeOfList; j++)
	                  if (list[j] < list[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = list[i];
	                  list[i] = list[minIndex];
	                  list[minIndex] = tmp;
	            }
	      }
	}

	public String toString() {
		String text = "[";
		for (int i = 0; i < sizeOfList; i++) {
			text = (i < sizeOfList - 1) ? text + list[i] + ", " : text + list[i];
		}
		text = text + "]";
		return text;
	}
}
