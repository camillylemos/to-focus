import createGlobalState from 'react-create-global-state'

const stringifyUser = localStorage.getItem('token')

const token = JSON.parse(stringifyUser) || {}

const [useGlobalToken, TokenGlobalProvider] = createGlobalState(token)

export { useGlobalToken, TokenGlobalProvider }
