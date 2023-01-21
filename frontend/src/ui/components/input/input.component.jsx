import { TextField } from '@mui/material'

const Input = ({ formData, handleChange }) => {
  return <TextField {...formData} variant="outlined" onChange={handleChange} />
}

export { Input }
