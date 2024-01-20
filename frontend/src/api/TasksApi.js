import { backendApi } from './backendApi'

const noteClient = backendApi('/tasks')

export const tasksApi = {
  getAll () {
    console.log('Fetching tasks')
    return noteClient.get('/sort-and-filter', { 
        params: {
            sortBy: 'disabled'
        }
    })
  },

  getById (id) {
    console.log('Get task', id)
    return noteClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    }
    })
  },

  create (task) {
    console.log('Create note', task)
    return noteClient.post('', task)
    },

    update (id, task) {
      console.log('Update note', id, task)
      return noteClient.put(`/${id}`, task)
    },

    delete (id) {
      console.log('Delete note', id)
      return noteClient.delete(`/${id}`)
    }
}