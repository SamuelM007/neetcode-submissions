class MyLinkedList {
    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index >= size || index < 0)
            return -1;

        return traverse(index).val;
    }

    public void addAtHead(int val) {
        size++;
        if (head == null) {
            head = new ListNode(val);
            tail = head;
            return;
        }

        head.prev = new ListNode(val, head);
        head = head.prev;
    }

    public void addAtTail(int val) {
        size++;
        if (tail == null) {
            tail = new ListNode(val);
            head = tail;
            return;
        }
        tail.next = new ListNode(tail, val);
        tail = tail.next;
    }

    public void addAtIndex(int index, int val) {
        if (index > size || index < 0)
            return;
        if (index == 0) {
            addAtHead(val);
            return;
        } else if (index == size) {
            addAtTail(val);
            return;
        }

        ListNode close = traverse(index);
        ListNode open = close.prev;

        open.next = new ListNode(open, val, close);
        close.prev = open.next;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        if (index == 0) {
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
            size--;
            return;
        }
        if (index == size - 1) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
            size--;
            return;
        }

        ListNode current = traverse(index);

        ListNode open = current.prev;
        ListNode close = current.next;

        open.next = close;
        close.prev = open;
        size--;
    }

    private ListNode traverse(int index) {
        ListNode current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}

class ListNode {
    public int val;
    public ListNode prev;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        prev = null;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.prev = null;
        this.next = next;
    }

    public ListNode(ListNode prev, int val) {
        this.val = val;
        this.prev = prev;
        this.next = null;
    }

    public ListNode(ListNode prev, int val, ListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}