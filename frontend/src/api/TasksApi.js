import { backendApi } from './backendApi'

const taskClient = backendApi('/tasks')

export const tasksApi = {
  getAll () {
    console.log('Fetching category')
    return taskClient.get('/sort-and-filter', { 
        params: {
            sortBy: 'disabled'
        }
    })
  },

  getById (id) {
    console.log('Get category', id)
    return taskClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    }
    })
  },

  getByCategoryNameAndSort (name, sortBy) {
    console.log('Get category', name)
    return taskClient.get(`/sort-and-filter`, {
      params: {
        categoryName: `${name}`,
        sortBy: `${sortBy}`
    }
    })
  },

  create (task) {
    console.log('Create category', task)
    return taskClient.post('', task)
    },

    update (id, task) {
      console.log('Update category', id, task)
      return taskClient.put(`/${id}`, task)
    },

    delete (id) {
      console.log('Delete category', id)
      return taskClient.delete(`/${id}`)
    }
}