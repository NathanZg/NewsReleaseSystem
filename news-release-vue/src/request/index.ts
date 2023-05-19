import axios from "axios";

const httpService = axios.create({
    baseURL: "/api",
    timeout: 5000,
    headers: {
        'content-type': 'application/json;charset=UTF-8',
    },
})

export default httpService
