// 定义接口，用于描述数据成员
export interface ICart {
  id: number;
  userId: number;
  bookId: number;
  quantity: number;
  totalPrice: number;
  addTime: Date;
}

// 定义类，用于实现功能函数
export class Cart implements ICart {
  id: number = 0;
  userId: number = 0;
  bookId: number = 0;
  quantity: number = 0;
  totalPrice: number = 0;
  addTime: Date = new Date();

  // 构造函数，仅接收接口定义的数据成员
  constructor(data: Partial<ICart> = {}) {
    this.id = data.id ?? 0;
    this.userId = data.userId ?? 0;
    this.bookId = data.bookId ?? 0;
    this.quantity = data.quantity ?? 0;
    this.totalPrice = data.totalPrice ?? 0;
    this.addTime = data.addTime ?? new Date();
  }

  // Getters
  getId(): number {
    return this.id;
  }

  getUserId(): number {
    return this.userId;
  }

  getBookId(): number {
    return this.bookId;
  }

  getQuantity(): number {
    return this.quantity;
  }

  getTotalPrice(): number {
    return this.totalPrice;
  }

  getAddTime(): Date {
    return this.addTime;
  }

  // Setters
  setId(id: number): void {
    this.id = id;
  }

  setUserId(userId: number): void {
    this.userId = userId;
  }

  setBookId(bookId: number): void {
    this.bookId = bookId;
  }

  setQuantity(quantity: number): void {
    this.quantity = quantity;
  }

  setTotalPrice(totalPrice: number): void {
    this.totalPrice = totalPrice;
  }

  setAddTime(addTime: Date): void {
    this.addTime = addTime;
  }

  // 删除方法（重置为默认值）
  deleteId(): void {
    this.id = 0;
  }

  deleteUserId(): void {
    this.userId = 0;
  }

  deleteBookId(): void {
    this.bookId = 0;
  }

  deleteQuantity(): void {
    this.quantity = 0;
  }

  deleteTotalPrice(): void {
    this.totalPrice = 0;
  }

  deleteAddTime(): void {
    this.addTime = new Date();
  }

  // 计算总价格的成员函数
  calculateTotalPrice(unitPrice: number): void {
    this.totalPrice = unitPrice * this.quantity;
  }
}


