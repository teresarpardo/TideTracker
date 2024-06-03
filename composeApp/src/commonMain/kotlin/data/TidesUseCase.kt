package data

import data.model.TidesDomainModel
import network.Result

interface TidesUseCase {
    suspend fun getTides(): Result<TidesDomainModel>
}

class TidesUseCaseImpl(
    private val tidesDataSource: TidesDataSource
) : TidesUseCase {
    override suspend fun getTides(): Result<TidesDomainModel> = tidesDataSource.getTides()
}
