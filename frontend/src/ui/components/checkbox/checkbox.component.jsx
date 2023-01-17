/* eslint-disable react/jsx-pascal-case */
import { Checkbox as _Checkbox } from '@mui/material'

const Checkbox = ({ value, handleChange }) => {
  return <_Checkbox {...value} onChange={handleChange} />
}

export { Checkbox }
