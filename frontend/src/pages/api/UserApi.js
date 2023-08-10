import AxiosApiBasedClass from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export default class UserApi extends AxiosApiBasedClass{
    static async singIn(body){
        const response = await axios.post(`http://localhost:80/api/users/auth`, body, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        return response
    }

    static  async signUp(body){
        const response = await axios.post(`http://localhost:80/api/users`, body, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        return response
    }
}