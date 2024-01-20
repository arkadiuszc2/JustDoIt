import { backendApi } from './backendApi'

const categoriesClient = backendApi('/categories')

export const categoriesApi = {
  getAll () {
    console.log('Fetching categories')
    return categoriesClient.get('');
  },

  getById (id) {
    console.log('Get task', id)
    return categoriesClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    }
    })
  },

  create (category) {
    console.log('Create note', category)
    return categoriesClient.post('', category)
    },

    update (id, category) {
      console.log('Update note', id, category)
      return categoriesClient.put(`/${id}`, category)
    },

    delete (id) {
      console.log('Delete note', id)
      return categoriesClient.delete(`/${id}`)
    }
}