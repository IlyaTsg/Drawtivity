import $api from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export default class UserApi {
    static async singIn(body){
        const response = await axios.post(`http://localhost/api/auth`, body)
        return response.data
    }

    static  async signUp(body){
        const response = await axios.post(`http://localhost/api/registration`, body)
        return response.data
    }
}