import request from '@/utils/request'

export function listCompany(query) {
  return request({
    url: '/basic/company/list',
    method: 'get',
    params: query
  })
}

export function createCompany(data) {
  return request({
    url: '/basic/company/create',
    method: 'post',
    data
  })
}

export function readminCompany(data) {
  return request({
    url: '/basic/company/readmin',
    method: 'get',
    data
  })
}

export function updateCompany(data) {
  return request({
    url: '/basic/company/update',
    method: 'post',
    data
  })
}

export function deleteCompany(data) {
  return request({
    url: '/basic/company/delete',
    method: 'post',
    data
  })
}

//////////////////////////////////////租户管理/////////////////////////////////////////////////
export function listTenant(query) {
  return request({
    url: '/basic/tenant/list',
    method: 'get',
    params: query
  })
}

export function createTenant(data) {
  return request({
    url: '/basic/tenant/create',
    method: 'post',
    data
  })
}

export function readminTenant(data) {
  return request({
    url: '/basic/tenant/readmin',
    method: 'get',
    data
  })
}

export function updateTenant(data) {
  return request({
    url: '/basic/tenant/update',
    method: 'post',
    data
  })
}

export function deleteTenant(data) {
  return request({
    url: '/basic/tenant/delete',
    method: 'post',
    data
  })
}
