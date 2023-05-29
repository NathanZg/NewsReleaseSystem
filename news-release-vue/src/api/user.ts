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
export function configManager(user: object) {
    return httpService({
        url: 'user/setAdmin',
        method: 'post',
        data: user
    })
}
export function userSelect(queryVo: object) {
    return httpService({
        url: 'user/lookAllUser',
        method: 'post',
        data: queryVo
    })
}
export function userDelete(ids: string) {
    return httpService({
        url: `user/delete/${ids}`,
        method: 'delete',
        data: ids,
        headers: {
            'Content-Type': 'text/plain'
        }
    })
}
export function userAdd(user: object) {
    return httpService({
        url: 'user/register',
        method: 'post',
        data: user
    })
}