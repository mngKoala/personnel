import request from '@/utils/request'

// ///////////////////////////////////人员统计////////////////////////////////////
export function listStat() {
  return request({
    url: '/contract/stat/list',
    method: 'get'
  })
}

export function listYear() {
  return request({
    url: '/contract/stat/year',
    method: 'get'
  })
}

// //////////////////////////////////////////人员合同/////////////////////////////////////////////
export function listPersonContract(query) {
  return request({
    url: '/contract/contract/list',
    method: 'get',
    params: query
  })
}

export function createPersonContract(data) {
  return request({
    url: '/contract/contract/create',
    method: 'post',
    data
  })
}

export function readminPersonContract(data) {
  return request({
    url: '/contract/contract/readmin',
    method: 'get',
    data
  })
}

export function updatePersonContract(data) {
  return request({
    url: '/contract/contract/update',
    method: 'post',
    data
  })
}

export function deletePersonContract(data) {
  return request({
    url: '/contract/contract/delete',
    method: 'post',
    data
  })
}

// //////////////////////////////////////////合同签订方/////////////////////////////////////////////
export function listContractOrg(query) {
  return request({
    url: '/contract/org/list',
    method: 'get',
    params: query
  })
}

export function createContractOrg(data) {
  return request({
    url: '/contract/org/create',
    method: 'post',
    data
  })
}

export function readminContractOrg(data) {
  return request({
    url: '/contract/org/readmin',
    method: 'get',
    data
  })
}

export function updateContractOrg(data) {
  return request({
    url: '/contract/org/update',
    method: 'post',
    data
  })
}

export function deleteContractOrg(data) {
  return request({
    url: '/contract/org/delete',
    method: 'post',
    data
  })
}
