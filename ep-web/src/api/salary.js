import request from '@/utils/request'


//////////////////////////////////////薪酬发放/////////////////////////////////////////////////
export function listSalaryAction(query) {
  return request({
    url: '/salary/action/list',
    method: 'get',
    params: query
  })
}

export function createSalaryAction(data) {
  return request({
    url: '/salary/action/create',
    method: 'post',
    data
  })
}

export function readminSalaryAction(data) {
  return request({
    url: '/salary/action/readmin',
    method: 'get',
    data
  })
}

export function updateSalaryAction(data) {
  return request({
    url: '/salary/action/update',
    method: 'post',
    data
  })
}

export function deleteSalaryAction(data) {
  return request({
    url: '/salary/action/delete',
    method: 'post',
    data
  })
}

//////////////////////////////////////薪酬参数/////////////////////////////////////////////////
export function listSalaryParameter(query) {
  return request({
    url: '/salary/parameter/list',
    method: 'get',
    params: query
  })
}

export function createSalaryParameter(data) {
  return request({
    url: '/salary/parameter/create',
    method: 'post',
    data
  })
}

export function readminSalaryParameter(data) {
  return request({
    url: '/salary/parameter/readmin',
    method: 'get',
    data
  })
}

export function updateSalaryParameter(data) {
  return request({
    url: '/salary/parameter/update',
    method: 'post',
    data
  })
}

export function deleteSalaryParameter(data) {
  return request({
    url: '/salary/parameter/delete',
    method: 'post',
    data
  })
}

//////////////////////////////////////薪酬年月/////////////////////////////////////////////////
export function listSalaryMonth(query) {
  return request({
    url: '/salary/month/list',
    method: 'get',
    params: query
  })
}

export function createSalaryMonth(data) {
  return request({
    url: '/salary/month/create',
    method: 'post',
    data
  })
}

export function readminSalaryMonth(data) {
  return request({
    url: '/salary/month/readmin',
    method: 'get',
    data
  })
}

export function updateSalaryMonth(data) {
  return request({
    url: '/salary/month/update',
    method: 'post',
    data
  })
}

export function deleteSalaryMonth(data) {
  return request({
    url: '/salary/month/delete',
    method: 'post',
    data
  })
}

//////////////////////////////////////薪酬模板/////////////////////////////////////////////////
export function listSalaryTemplate(query) {
  return request({
    url: '/salary/template/list',
    method: 'get',
    params: query
  })
}

export function createSalaryTemplate(data) {
  return request({
    url: '/salary/template/create',
    method: 'post',
    data
  })
}

export function readminSalaryTemplate(data) {
  return request({
    url: '/salary/template/readmin',
    method: 'get',
    data
  })
}

export function updateSalaryTemplate(data) {
  return request({
    url: '/salary/template/update',
    method: 'post',
    data
  })
}

export function deleteSalaryTemplate(data) {
  return request({
    url: '/salary/template/delete',
    method: 'post',
    data
  })
}