import AxiosApiBasedClass from '../../shared/api/AxiosApiBasedClass';
import $api from '../../shared/api/AxiosApiBasedClass';

export class TaskApi {
  static async createTask(data) {
    const response = await $api.post('/tasks', data);

    return response;
  }

  static async createSolution(body) {
    const response = await $api.post('/tasks/solution', {
      task_id: body.task_id,
      points: body.points,
    });
    return response;
  }

  static async getActualTask(id) {
    const response = await $api.get('/tasks/' + id);
    return response.data;
  }

  static async getTasks() {
    //const response = await axios.get('http://localhost:80/api/tasks')
    //console.log(response)
    const response2 = await $api.get('/tasks');
    return response2.data;
  }

  static async getTasksById(id) {
    //const response = await axios.get('http://localhost:80/api/tasks')
    //console.log(response)
    const response2 = await $api.get('/tasks/' + id);
    return response2.data;
  }
}