import $api from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export default class UserApi {
    static async singIn(body){
        const response = await $api.post(`/auth`, body, {
                "Access-Control-Allow-Origin": 'http://localhost:3000'
        })
        return response
    }

    static  async signUp(body){
        const response = await $api.post(`/registration`, body,
            {
                "Access-Control-Allow-Origin": 'http://localhost:3000'
            })
        return response
    }
}