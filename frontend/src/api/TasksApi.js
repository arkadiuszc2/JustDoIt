import { backendApi } from './backendApi'

const taskClient = backendApi('/tasks')

export const tasksApi = {
  getAll () {
    console.log('Fetching tasks')
    return taskClient.get('/sort-and-filter', { 
        params: {
            sortBy: 'disabled'
        }
    })
  },

  getById (id) {
    console.log('Get task', id)
    return taskClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    }
    })
  },

  create (task) {
    console.log('Create note', task)
    return taskClient.post('', task)
    },

    update (id, task) {
      console.log('Update note', id, task)
      return taskClient.put(`/${id}`, task)
    },

    delete (id) {
      console.log('Delete note', id)
      return taskClient.delete(`/${id}`)
    }
}