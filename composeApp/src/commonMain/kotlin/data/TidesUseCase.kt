package data

import data.model.TidesDomainModel
import network.Result

interface TidesUseCase {
    suspend fun getTides(): Result<TidesDomainModel>
}

class TidesUseCaseImpl : TidesUseCase {
    override suspend fun getTides(): Result<TidesDomainModel> = TidesDataSourceImpl().getTides()
}
