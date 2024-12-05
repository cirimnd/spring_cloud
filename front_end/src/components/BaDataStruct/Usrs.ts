// Role 枚举
export enum Role {
    ADMIN = "管理员",
    USER = "用户",
}

// User 类
export class User {
    userid: number = 0;
    userName: string = '';
    password: string = '';
    phone: string = '';
    role: Role = Role.USER;

    // 构造函数
    constructor(data: Partial<User> = {}) {
        this.userid = data.userid ?? 0;
        this.userName = data.userName ?? '';
        this.password = data.password ?? '';
        this.phone = data.phone ?? '';
        this.role = data.role ?? Role.USER;
    }

    // Getters
    getUserid(): number {
        return this.userid;
    }

    getUserName(): string {
        return this.userName;
    }

    getPassword(): string {
        return this.password;
    }

    getPhone(): string {
        return this.phone;
    }

    getRole(): Role {
        return this.role;
    }

    // Setters
    setUserid(userid: number): void {
        this.userid = userid;
    }

    setUserName(userName: string): void {
        this.userName = userName;
    }

    setPassword(password: string): void {
        this.password = password;
    }

    setPhone(phone: string): void {
        this.phone = phone;
    }

    setRole(role: Role): void {
        this.role = role;
    }

    // 删除方法（重置为默认值）
    resetUserid(): void {
        this.userid = 0;
    }

    resetUserName(): void {
        this.userName = '';
    }

    resetPassword(): void {
        this.password = '';
    }

    resetPhone(): void {
        this.phone = '';
    }

    resetRole(): void {
        this.role = Role.USER;
    }
}
