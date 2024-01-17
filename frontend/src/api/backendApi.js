import axios from 'axios'

export const backendApi = (url) => {
    const urlForDev = 'http://localhost:8080' //temporal url for development and testing

    const client = axios.create({
      baseURL: urlForDev + url,
      headers: {
        'Content-Type': 'application/json',
        Accept: 'application/json'
      }
    })

    client.interceptors.response.use(response => {
        return response
      }, function (error) {
        console.log('An error occurred while calling backend', error)
        if (error.response) {
          // client received an error response (5xx, 4xx)
          if (error.response.status === 404) {
            return { status: error.response.status }
          }
          return Promise.reject(error.response)
        } else if (error.request) {
          // client never received a response (we can set custom timeout for axios request), 
          // or request never left
        } else {
          // anything else
        }
      })
    
      return client
    }