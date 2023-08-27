import axios from "axios";

const BASE_URL = "http://localhost:80/api"

const $api = axios.create({
        withCredentials: true,
        baseURL: BASE_URL
})
$api.interceptors.request.use((config) => {
    config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`
    return config
})
export default $api
