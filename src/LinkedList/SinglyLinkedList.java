package LinkedList;

/**
 * LinkedList 구현
 */
public class SinglyLinkedList<E> {
	private Node<E> head; // 맨 처음의 주소를 가리키는 Node
	private Node<E> tail; // 맨 마지막 주소를 가리키는 Node

	private int size; // List의 크

	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;

	}

	// Node 객체 생
	private static class Node<E> {
		private E item; // Node에 담을 데이터
		private Node<E> next; // 다음 객체를 가르키는 주소

		// 생성자
		Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}

	// 검색
	private Node<E> search(int index) {
		Node<E> n = head;

		// index에 해당하는 위치까지 찾아갈 수 있게 반복문을 통한 검색
		// next는 말 그대로 다음 위치를 가리키기 때문에 index-1 까지만 돌려야
		for (int i = 0; i < index - 1; i++) {
			n = n.next;
		}

		return n;
	}

	// 맨 앞에 추가
	private void addFirst(E value) {
		Node<E> first = head;

		Node<E> newNode = new Node<>(value, first); // 새로운 노드 생성
		size++; // 사이즈 추가

		head = newNode;

		if (first == null) {
			tail = newNode;
		}
	}

	// 맨 뒤에  추가
	private void addLast(E value) {
		// 1. tail 값 저장
		Node<E> last = tail;

		// 2. 맨 뒤에 data 추가
		Node<E> newNode = new Node<>(value, null);

		// 3. size 추가
		size++;

		// 4. tail값 수정
		tail = newNode;

		// 5. last의 next에 새로운 node의 주소를 넣음
		if (last == null) {
			head = newNode;
		} else
			last.next = newNode;
	}
	
	// LinkedList의 add 구현 (실제로 addLast가 호출됨)
	public boolean add(E value){
		addLast(value);
		return true;
	}
	
	// 중간에 삽입하기
	public void add(int index, E value) {
		// index를 벗어날 시 오류 반환
		if(index<0 || index > size -1) {
			throw new IndexOutOfBoundsException();
		}	
		
		// index가 0이라면 addFirst
		if(index==0) {
			addFirst(value);
			return;
		}
		
		// index가 size-1이라면 addLast
		if(index==size-1) {
			addLast(value);
			return;
		}
		
		// 이전 노드 찾기
		Node<E> previous_Node = search(index-1);
		
		// 이전 노드의 next를 통해 다음 노드 찾기
		Node<E> next_Node = previous_Node.next;
		
		// 새로운 노드 생성 
		Node<E> newNode = new Node<>(value, next_Node);
		
		// 이전 노드의 next 값을 새 노드의 주소로 변
		previous_Node.next = newNode;
		
		// 사이즈 추
		size++;
	}
}













