export interface Iorder{
    orderId: number,
     userId: number,
      bookId: number,
      bookPrice: number,
      state: number,
      orderDatetime: Date,
      address: string,
      bookname: string,
}

export class Order implements Iorder {
    orderId: number=0;
    userId: number=0;
    bookId: number=0;
    bookPrice: number =0;
    state: number=0; // 0 表示未到货, 1 表示到货
    orderDatetime: Date ;
    address: string='';
    bookname: string='';

   constructor(data: Partial<Iorder> = {}) {
        this.orderId = data.orderId || 0;
        this.userId = data.userId || 0;
        this.bookId = data.bookId || 0;
        this.bookPrice = data.bookPrice || 0;
        this.state = data.state || 0;
        this.orderDatetime = data.orderDatetime ||new Date();
        this.address = data.address || '';
        this.bookname=data.bookname || '';
    }

    // Getters
    getOrderId(): number | null {
        return this.orderId;
    }

    getUserId(): number | null {
        return this.userId;
    }

    getBookId(): number | null {
        return this.bookId;
    }

    getBookPrice(): number | null {
        return this.bookPrice;
    }

    getState(): number | null {
        return this.state;
    }

    getOrderDatetime(): Date | null {
        return this.orderDatetime;
    }

    getAddress(): string | null {
        return this.address;
    }
    getBookname(): string | null {
        return this.bookname;
    }

    // Setters
    setOrderId(orderId: number): void {
        this.orderId = orderId;
    }

    setUserId(userId: number): void {
        this.userId = userId;
    }

    setBookId(bookId: number): void {
        this.bookId = bookId;
    }

    setBookPrice(bookPrice: number): void {
        this.bookPrice = bookPrice;
    }

    setState(state: number): void {
        this.state = state;
    }

    setOrderDatetime(orderDatetime: Date): void {
        this.orderDatetime = orderDatetime;
    }

    setAddress(address: string): void {
        this.address = address;
    }

    setBookname(name: string): void {
        this.bookname = name;
    }


    // Methods to delete properties (reset to default values)
    deleteOrderId(): void {
        this.orderId = 0;
    }

    deleteUserId(): void {
        this.userId = 0;
    }

    deleteBookId(): void {
        this.bookId = 0;
    }

    deleteBookPrice(): void {
        this.bookPrice = 0;
    }

    deleteState(): void {
        this.state = 0;
    }

    deleteOrderDatetime(): void {
        
    }

    deleteAddress(): void {
        this.address = '';
    }
}
