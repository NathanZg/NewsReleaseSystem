import httpService from "@/request"
export function typeAdd(type: object) {
    return httpService({
        url: 'typeAdd',
        method: 'post',
        data: type
    })
}
export function typeSelect() {
    return httpService({
        url: 'typeSelect',
        method: 'post'
    })
}
export function typeDelete(ids: string) {
    return httpService({
        url: 'typeDelete',
        method: 'delete',
        data: ids,
        headers: {
            'Content-Type': 'text/plain'
        }
    })
}