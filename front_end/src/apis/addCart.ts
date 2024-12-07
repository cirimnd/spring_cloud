import httpRequest from '@/request/index'

// 
export function apiAddCart(userId:number,bookId:number,quantity:number) {
    return httpRequest({
		url: `http://localhost:9999/cart/add?userId=${userId}&bookId=${bookId}&quantity=${quantity}`,
		method: 'post'
	})
}
