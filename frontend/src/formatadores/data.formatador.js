const adicionarFormatacaoInglesParaPortugues = data => {
  const [ano, mes, dia] = data.split('-')

  return `${dia}/${mes}/${ano}`
}

const adicionarFormatacaoPortuguesParaIngles = data => {
  const [dia, mes, ano] = data.split('/')

  return `${ano}-${mes}-${dia}`
}

export { adicionarFormatacaoInglesParaPortugues, adicionarFormatacaoPortuguesParaIngles }
