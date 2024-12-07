import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetBooksByName(bookName:string) {
    return httpRequest({
		url: `http://localhost:9999/book/name?name=${bookName}`,
		method: 'get',
	})
}