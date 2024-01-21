import { backendApi } from './backendApi'
import { bearerAuth } from './bearerAuth'

const categoriesClient = backendApi('/categories')

export const categoriesApi = {
  getAll (token) {
    console.log('Fetching categories')
    return categoriesClient.get('', {
      headers: { Authorization: bearerAuth(token) }
    });
  },

  getById (id, token) {
    console.log('Get task', id)
    return categoriesClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    },
    headers: { Authorization: bearerAuth(token) }
    })
  },

  create (category, token) {
    console.log('Create note', category)
    return categoriesClient.post('', category, {
      headers: { Authorization: bearerAuth(token) }
    })
    },

    update (id, category, token) {
      console.log('Update note', id, category)
      return categoriesClient.put(`/${id}`, category, {
        headers: { Authorization: bearerAuth(token) }
      })
    },

    delete (id, token) {
      console.log('Delete note', id)
      return categoriesClient.delete(`/${id}`, {
        headers: { Authorization: bearerAuth(token) }
      })
    }
}