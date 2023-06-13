import AxiosApiBasedClass from "../../shared/api/AxiosApiBasedClass";
import axios from "axios";

export class TaskApi extends AxiosApiBasedClass{
    static async createTask(data){
        console.log(data)
        const response = await axios.post('http://localhost:80/api/tasks', data,{
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return response
    }

    static async createSolution(body) {
        const response = await axios.post('http://localhost:80/api/tasks/solution',{
          body
        },{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }

    static async getActualTask(id){
        const response = await axios.get('http://localhost:80/api/tasks/' + id)
        return response.data
    }

    static async getTasks(){
        //const response = await axios.get('http://localhost:80/api/tasks')
        //console.log(response)
        const response2 = await axios.get('http://localhost:80/api/tasks')
        return response2.data
    }
}