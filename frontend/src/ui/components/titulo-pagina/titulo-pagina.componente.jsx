import { Helmet } from 'react-helmet-async'
import PropTypes from 'prop-types'

import './titulo-pagina.estilo.scss'

const TituloPagina = ({ titulo, className = '', tituloVisivel, ...props }) => {
  return (
    <>
      <Helmet>
        <title>{`${titulo} | To Focus`}</title>
      </Helmet>
      <h1
        data-cy="titulo-principal"
        className={`c-titulo ${className}`}
        {...props}
      >
        {tituloVisivel || titulo}
      </h1>
    </>
  )
}

TituloPagina.propTypes = {
  titulo: PropTypes.string.isRequired,
  tituloVisivel: PropTypes.string,
  className: PropTypes.string,
}

TituloPagina.defaultProps = {
  tituloVisivel: '',
}

export { TituloPagina }
