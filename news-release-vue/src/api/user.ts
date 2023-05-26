import httpService from "@/request"
export function userLogin(user: object) {
    return httpService({
        url: 'user/login',
        method: 'post',
        data: user
    })
}
export function userRegister(user: object) {
    return httpService({
        url: 'user/register',
        method: 'post',
        data: user
    })
}