/* eslint-disable react/jsx-pascal-case */
import { CheckCircle, CircleOutlined } from '@mui/icons-material'
import { Checkbox as _Checkbox } from '@mui/material'

const Checkbox = ({ value, handleChange }) => {
  return (
    <_Checkbox
      icon={<CircleOutlined />}
      checkedIcon={<CheckCircle />}
      {...value}
      onChange={handleChange}
      color="third"
    />
  )
}

export { Checkbox }
