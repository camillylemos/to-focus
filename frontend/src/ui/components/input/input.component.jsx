import { TextField } from '@mui/material'

const Input = ({ formData, handleChange, ...rest }) => {
  return <TextField {...formData} variant="outlined" onChange={handleChange} {...rest} />
}

export { Input }
