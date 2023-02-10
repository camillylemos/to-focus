import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { Button } from '@mui/material'
import { Form, Input } from '@components'
import { UseUser } from '@hooks'

// import './login.style.scss'

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
  name: {
    name: 'name',
    label: 'Nome',
  },
  bdayDate: {
    name: 'bdayDate',
    label: 'Data de Nascimento',
  },
}

const CadastroScreen = () => {
  const [formData, setFormData] = useState(FORM_DATA_INITIAL)

  const navigate = useNavigate()

  const { register } = UseUser()

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
        nome: values.name,
        dataNascimento: values.bdayDate,
      }

      await register(data)

      navigate('/login')
      //todo verificar aqui
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
      <Input formData={formData.name} handleChange={handleChange} />
      <Input formData={formData.email} handleChange={handleChange} />
      <Input formData={formData.bdayDate} handleChange={handleChange} />
      <Input formData={formData.password} handleChange={handleChange} />
      <Button type="submit">Cadastrar</Button>
    </Form>
  )
}

export { CadastroScreen }
