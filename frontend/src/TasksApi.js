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
  }
}