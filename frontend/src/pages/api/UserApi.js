import $api from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export default class UserApi {
    static async singIn(body){
        const response = await $api.post(`/auth`, body)
        return response
    }

    static  async signUp(body){
        const response = await $api.post(`http://localhost:80/api/registration`, body)
        return response
    }
}