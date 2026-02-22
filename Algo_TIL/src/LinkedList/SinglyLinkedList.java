package LinkedList;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * LinkedList 구현
 */
public class SinglyLinkedList<E> {
	private Node<E> head; // 맨 처음의 주소를 가리키는 Node
	private Node<E> tail; // 맨 마지막 주소를 가리키는 Node

	private int size; // List의 크기

	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;

	}

	// Node 객체 생성
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

	// 맨 뒤에 추가
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
	public boolean add(E value) {
		addLast(value);
		return true;
	}

	// 중간에 삽입하기
	public void add(int index, E value) {
		// index를 벗어날 시 오류 반환
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		// index가 0이라면 addFirst
		if (index == 0) {
			addFirst(value);
			return;
		}

		// index가 size-1이라면 addLast
		if (index == size - 1) {
			addLast(value);
			return;
		}

		// 이전 노드 찾기
		Node<E> previous_Node = search(index - 1);

		// 이전 노드의 next를 통해 다음 노드 찾기
		Node<E> next_Node = previous_Node.next;

		// 새로운 노드 생성
		Node<E> newNode = new Node<>(value, next_Node);

		// 이전 노드의 next 값을 새 노드의 주소로 변경
		previous_Node.next = newNode;

		// 사이즈 추가
		size++;
	}

	// 첫 요소 삭제 후 삭제된 요소 반환
	public E removeFirst() {
		// 1. 비어있는 리스트라면 오류
		if (head == null) {
			throw new IndexOutOfBoundsException();
		}

		// 2. 삭제할 first 노드
		Node<E> first = head;

		// 3. head를 다음 노드에 연결
		head = first.next;

		// 4. 반환할 값
		E returnValue = first.item;

		// 5. NULL로 초기화 시킨 후 GC가 삭제할 수 있도록 유도
		first.next = null;
		first.item = null;

		// 6. 사이즈 감소
		size--;

		// 7. 만약 삭제를 통해 list가 빈 상태가 되었다면
		if (head == null) {
			tail = null;
		}

		// 8. 반환
		return returnValue;
	}

	// LinkedList의 remove 동작
	public E remove() {
		return removeFirst();
	}

	// Index로 remove 구현
	public E remove(int index) {
		// 1. 범위를 벗어날 시 오류 출력
		if (index < 0 || index >= size || size == 0) {
			throw new IndexOutOfBoundsException();
		}

		// 2. index가 0이라면 removeFirst 호출
		if (index == 0) {
			return removeFirst();
		}

		// 3. 타겟 노드의 이전 노드 찾기
		Node<E> prev_Node = search(index - 1);

		// 4. 타겟 노드 찾기
		Node<E> targetNode = prev_Node.next;

		// 5. 이전 노드의 다음 주소를 타겟 노드의 다음 주소 값으로 변경
		prev_Node.next = targetNode.next;

		// 6. 삭제될 요소의 데이터 백업
		E returnValue = targetNode.item;

		// 7. 타겟 노드가 끝 부분이라면 tail 업데이트
		if (targetNode == tail) {
			tail = prev_Node;
		}

		// 메모리 정리
		targetNode.item = null;
		targetNode.next = null;

		size--;

		return returnValue;
	}

	// 맨 뒤 삭제
	public E removeLast() {

		// 사이즈 검사
		if (size == 0) {
			throw new NoSuchElementException();
		}

		// 사이즈 1 이면 맨 앞 삭제
		if (size == 1) {
			return removeFirst();
		}

		// target의 이전 값 저장
		Node<E> prev = search(size - 2);
		Node<E> target = tail;

		E returnValue = target.item;

		prev.next = null;
		tail = prev;

		target.item = null;
		size--;

		return returnValue;
	}

	// 값으로 remove 구현
	public boolean remove(Object value) {
		// 1. 만약 리스트가 비었다면 오류
		if (head == null) {
			return false;
		}

		// 2. 이전 노드, 타겟 노드 저장 변수 선언
		Node<E> prev = null;
		Node<E> target = null;

		// 3. 루프 시작 변수 선언
		Node<E> i = head;

		while (i != null) {
			if (Objects.equals(i.item, value)) {
				target = i;
				break;
			}
			// 이전 값에 i를 계속 할당
			prev = i;

			// i++과 동일한 동작
			i = i.next;
		}

		// 못 찾으면 false 리턴
		if (target == null) {
			return false;
		}

		// 삭제하려는 노드가 head라면 removeFirst;
		if (target == head) {
			removeFirst();
			return true;
		}

		if (target == tail) {
			removeLast();
			return true;
		}

		// 찾았다면 삭제
		prev.next = target.next;

		target.item = null;
		target.next = null;

		size--;
		return true;
	}

	// 특정 위치의 데이터 조회
	public E get(int index) {
		// 1. 범위를 벗어날 시 오류 출력 (size가 0인 경우 포함)
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// 2. search 메서드를 통해 해당 인덱스의 노드를 찾음
		// search(index)는 내부적으로 루프를 돌며 해당 위치의 Node를 반환함
		Node<E> targetNode = search(index);

		// 3. 해당 노드가 가지고 있는 데이터(item) 반환
		return targetNode.item;
	}

}
