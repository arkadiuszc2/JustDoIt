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

  create (note) {
    console.log('Create note', note)
    return noteClient.post('', note)
    }
}