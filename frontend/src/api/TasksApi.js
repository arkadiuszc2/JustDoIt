import { backendApi } from './backendApi'
import { bearerAuth } from './bearerAuth'

const taskClient = backendApi('/tasks')

export const tasksApi = {
  getAll (token) {
    console.log('Fetching category')
    return taskClient.get('/sort-and-filter', { 
        params: {
            sortBy: 'disabled'
        },
        headers: { Authorization: bearerAuth(token) }
    })
  },

  getById (id, token) {
    console.log('Get category', id)
    return taskClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    },
    headers: { Authorization: bearerAuth(token) }
    })
  },

  getByCategoryNameAndSort (name, sortBy, token) {
    console.log('Get category', name)
    return taskClient.get(`/sort-and-filter`, {
      params: {
        categoryName: `${name}`,
        sortBy: `${sortBy}`
    },
    headers: { Authorization: bearerAuth(token) }
    })
  },

  create (task, token) {
    console.log('Create category', task)
    return taskClient.post('', task, {
      headers: { Authorization: bearerAuth(token) }
    })
    },

    update (id, task, token) {
      console.log('Update category', id, task)
      return taskClient.put(`/${id}`, task, {
        headers: { Authorization: bearerAuth(token) }
      })
    },

    delete (id, token) {
      console.log('Delete category', id)
      return taskClient.delete(`/${id}`, {
        headers: { Authorization: bearerAuth(token) }
      })
    }
}