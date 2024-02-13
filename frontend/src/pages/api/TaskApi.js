import AxiosApiBasedClass from '../../shared/api/AxiosApiBasedClass'
import $api from '../../shared/api/AxiosApiBasedClass'

export class TaskApi {
  static async createTask(data) {
    console.log(data)
    const response = await $api.post('/tasks', data)

    return response
  }

  static async createSolution(body) {
    const response = await $api.post('/tasks/solution', {
      body,
    })
  }

  static async getActualTask(id) {
    const response = await $api.get('/tasks/' + id)
    return response.data
  }

  static async getTasks() {
    //const response = await axios.get('http://localhost:80/api/tasks')
    //console.log(response)
    const response2 = await $api.get('/tasks')
    console.log(response2)
    return response2.data
  }
}