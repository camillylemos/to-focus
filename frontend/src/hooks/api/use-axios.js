import axios from 'axios'
import { RequestParams } from '@utils'
import { METODOS_API } from '@constants'

const requisicaoAxios = axios.create({
  baseURL: 'http://localhost:8080/',
})

const useAxios = () => {
  const chamarAPI = async (method, url, dadosRequisicao, config = {}) => {
    const { headers } = config

    const configRequisicao = {
      method,
      url,
      data: dadosRequisicao,
      headers,
      ...config,
    }

    try {
      const requisicao = await requisicaoAxios.request(configRequisicao)
      return requisicao.data
    } catch (requisicaoFalha) {
      const { response } = requisicaoFalha
      return response?.data
    }
  }

  const get = (url, dados, config) => {
    return chamarAPI(METODOS_API.GET, RequestParams({ url, dados }), null, config)
  }

  const post = (url, data, config) => chamarAPI(METODOS_API.POST, url, data, config)

  const put = (url, data, config) => chamarAPI(METODOS_API.PUT, url, data, config)

  const patch = (url, data, config) => chamarAPI(METODOS_API.PATCH, url, data, config)

  const del = (url, config) => chamarAPI(METODOS_API.DELETE, url, null, config)

  return {
    get,
    post,
    put,
    del,
    patch,
  }
}

export { useAxios }
