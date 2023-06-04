import AxiosApiBasedClass from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export class TaskApi extends AxiosApiBasedClass{
    static async createTask(data){
        const response = await axios.post('http://localhost:80/api/tasks', data,{
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        return response
    }
}