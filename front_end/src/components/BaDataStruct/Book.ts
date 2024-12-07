export interface IBook {
    title: string;
    isbn: string;
    author: string;
    price: number;
    category: string;
    stock: number;
    description: string;
    Id:number;
}


export class Book implements IBook {
    title: string = '';
    isbn: string = '';
    author: string = '';
    price: number = 0;
    category: string = '';
    stock: number = 0;
    description: string = '';
    Id: number=0;

    constructor(data: any = {}) {
        this.title = data.title || '';
        this.isbn = data.isbn || '';
        this.author = data.author || '';
        this.price = data.price || 0;
        this.category = data.category || '';
        this.stock = data.stock || 0;
        this.description = data.description || '';
        this.Id=data.Id || data.id || 0;
    }

    // Getters
    getTitle(): string {
        return this.title;
    }

    getIsbn(): string {
        return this.isbn;
    }

    getAuthor(): string {
        return this.author;
    }

    getPrice(): number {
        return this.price;
    }

    getCategory(): string {
        return this.category;
    }

    getStock(): number {
        return this.stock;
    }

    getDescription(): string {
        return this.description;
    }

    // Setters
    setTitle(title: string): void {
        this.title = title;
    }

    setIsbn(isbn: string): void {
        this.isbn = isbn;
    }

    setAuthor(author: string): void {
        this.author = author;
    }

    setPrice(price: number): void {
        this.price = price;
    }

    setCategory(category: string): void {
        this.category = category;
    }

    setStock(stock: number): void {
        this.stock = stock;
    }

    setDescription(description: string): void {
        this.description = description;
    }

    // Methods to delete properties
    deleteTitle(): void {
        this.title = '';
    }

    deleteIsbn(): void {
        this.isbn = '';
    }

    deleteAuthor(): void {
        this.author = '';
    }

    deletePrice(): void {
        this.price = 0;
    }

    deleteCategory(): void {
        this.category = '';
    }

    deleteStock(): void {
        this.stock = 0;
    }

    deleteDescription(): void {
        this.description = '';
    }
}
