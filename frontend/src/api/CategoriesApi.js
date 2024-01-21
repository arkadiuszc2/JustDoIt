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
    console.log('Get category', id)
    return categoriesClient.get(`/${id}`, {
      params: {
        searchBy: 'id'
    },
    headers: { Authorization: bearerAuth(token) }
    })
  },

  create (category, token) {
    console.log('Create category', category)
    return categoriesClient.post('', category, {
      headers: { Authorization: bearerAuth(token) }
    })
    },

    update (id, category, token) {
      console.log('Update category', id, category)
      return categoriesClient.put(`/${id}`, category, {
        headers: { Authorization: bearerAuth(token) }
      })
    },

    delete (id, token) {
      console.log('Delete category', id)
      return categoriesClient.delete(`/${id}`, {
        headers: { Authorization: bearerAuth(token) }
      })
    }
}