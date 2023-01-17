import { TextareaAutosize } from '@mui/material'

const Textarea = ({ formData, handleChange }) => {
  return (
    <TextareaAutosize {...formData} minRows={3} style={{ width: 500 }} onChange={handleChange} />
  )
}

export { Textarea }
