import httpRequest from '@/request/index'

// 定义接口的传参
interface BookInfoParam {
    userId:number,
    bookId:number,
    bookPrice:number,
    state:number,
    address:string  
}

// 
export function apiAddOrder(param: BookInfoParam) {
    return httpRequest({
		url: 'http://localhost:8081/order/edit',
		method: 'post',
		data: param,
	})
}