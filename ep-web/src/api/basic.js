import request from '@/utils/request'

export function listEmployee(query) {
  return request({
    url: '/basic/employee/list',
    method: 'get',
    params: query
  })
}

export function createEmployee(data) {
  return request({
    url: '/basic/employee/create',
    method: 'post',
    data
  })
}

export function readminEmployee(data) {
  return request({
    url: '/basic/employee/readmin',
    method: 'get',
    data
  })
}

export function updateEmployee(data) {
  return request({
    url: '/basic/employee/update',
    method: 'post',
    data
  })
}

export function deleteEmployee(data) {
  return request({
    url: '/basic/employee/delete',
    method: 'post',
    data
  })
}

// ///////////////////////////////岗位/////////////////////////////////////////
export function listPosition(query) {
  return request({
    url: '/basic/position/list',
    method: 'get',
    params: query
  })
}

export function createPosition(data) {
  return request({
    url: '/basic/position/create',
    method: 'post',
    data
  })
}

export function readminPosition(data) {
  return request({
    url: '/basic/position/readmin',
    method: 'get',
    data
  })
}

export function updatePosition(data) {
  return request({
    url: '/basic/position/update',
    method: 'post',
    data
  })
}

export function deletePosition(data) {
  return request({
    url: '/basic/position/delete',
    method: 'post',
    data
  })
}

// ///////////////////////////////岗位类型/////////////////////////////////////////
export function listPositionTpye(query) {
  return request({
    url: '/basic/position/type/list',
    method: 'get',
    params: query
  })
}

export function createPositionTpye(data) {
  return request({
    url: '/basic/position/type/create',
    method: 'post',
    data
  })
}

export function readminPositionTpye(data) {
  return request({
    url: '/basic/position/type/readmin',
    method: 'get',
    data
  })
}

export function updatePositionTpye(data) {
  return request({
    url: '/basic/position/type/update',
    method: 'post',
    data
  })
}

export function deletePositionTpye(data) {
  return request({
    url: '/basic/position/type/delete',
    method: 'post',
    data
  })
}
