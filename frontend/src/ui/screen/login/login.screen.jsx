import { Form, Input } from '@components'
import { useGlobalToken } from '@contexts'
import { Button } from '@mui/material'
import { useAuthentication } from 'hooks/api/use-authentication.hook'
import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import './login.style.scss'

const FORM_DATA_INITIAL = {
  email: {
    name: 'email',
    label: 'E-mail',
  },
  password: {
    name: 'password',
    label: 'Senha',
    type: 'password',
  },
}

const LoginScreen = () => {
  const [formData, setFormData] = useState(FORM_DATA_INITIAL)
  const [, setToken] = useGlobalToken()

  const navigate = useNavigate()

  const { login } = useAuthentication()

  useEffect(() => {
    if (JSON.parse(localStorage.getItem('token'))) {
      navigate('/')
    }
  }, [navigate])

  const handleSubmit = async ({ isValid, values }) => {
    if (isValid && values) {
      const data = {
        email: values.email,
        senha: values.password,
      }

      const response = await login(data)

      if (response) {
        setToken(response.token)
        localStorage.setItem('token', JSON.stringify(response.token))
        navigate('/')
      }
    }
  }

  const handleChange = event => {
    const { name, value } = event.target

    setFormData(formData => ({
      ...formData,
      [name]: {
        ...formData[name],
        value,
        name,
      },
    }))
  }

  return (
    <Form formData={formData} onSubmit={handleSubmit}>
      <Input formData={formData.email} handleChange={handleChange} />
      <Input formData={formData.password} handleChange={handleChange} />
      <Button type="submit">Entrar</Button>
    </Form>
  )
}

export { LoginScreen }
